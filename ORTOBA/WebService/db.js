//init some framework/library/dependency
var mysql = require('mysql');

var fs = require('fs');

var config = JSON.parse(fs.readFileSync('./mysql-config.json'));

var client = new mysql.createConnection(config);

client.connect(function(err) {
  // connected! (unless `err` is set)
});

function hasToClause(hash, separator)
{
	var result = '';
	var values = new Array();
	var first = true;
	for(var key in hash)
	{
		result += (first ? '' : separator) + key + ' = ?';
		values.push(hash[key]);
		first = false;
	}
	return {clause : result, values : values};
}

function insert(table, values, callback)
{
	var q = 'INSERT INTO ' + table + ' SET ';
	var clause = hasToClause(values, ', ');
	q += clause.clause + ';';

	client.query(q, clause.values, callback);
}

function remove(table, where, callback)
{
	var q = 'DELETE FROM' + table + ' WHERE ';
	var clause = hasToClause(where, ' AND ');
	q += clause.clause;
	client.query(q, clause.values, callback); 
}


function read(table, where, columns, callback)
{
	var columnsClause = (columns ? columns.join(', ') : '*');
	var q = 'SELECT ' + columnsClause + ' FROM ' + table;
	if(where)
	{
		var clause = hasToClause(where, ' AND ');
		q += ' WHERE ' + clause.clause;
	}
	client.query(q, (where ? clause.values : callback), callback);
}

function update(table, where, values, callback)
{
	var whereClause = hasToClause(where, ' AND ');
	var valuesClause = hasToClause(values, ' AND ');
	var q = 'UPDATE ' + table + ' SET ' + valuesClause.clause + ' WHERE ' + whereClause.clause + ';';
	client.query(q, whereClause.values.concat(valuesClause.values), callback);
}


exports.insert = insert;
exports.remove = remove;
exports.read = read;
exports.update = update;


exports.updateById = function(table, id, values, callback)
{
	update(table, { 'id' : id}, values, callback);
};

exports.find = function(table, id, callback)
{
	read(table, { 'id' : id }, null, callback);
};

exports.removeById = function(table, id, callback)
{
	remove(table, { 'id' : id }, callback);
};

exports.findAll = function(table, callback)
{
	read(table, null, null, callback);
};

exports.query = function(query, values, callback)
{
	return client.query(query, values, callback);
};