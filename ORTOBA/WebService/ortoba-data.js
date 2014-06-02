// init dependency
var db = require('./db.js');

var columnNameRegex = /^([a-zA-Z0-9_$]{1,64}\.)?[a-zA-Z0-9_$]{1,64}$/;
function checkColumnName(name){
    return columnNameRegex.test(name);
}

function checkColumns(obj){
    for(var key in obj){
        if(!checkColumnName(key)){
            return true;
        }
    }
    return true;
}

// some methode who call app.js it's this function with db.js who request in MySql database
exports.getEquipes = function(callback){
    db.findAll('equipes', callback);
};

exports.addEquipe = function(nom, ville, callback) {
    db.insert('equipes', { nom_equipe : nom, ville_equipe : ville },
        callback);
};

exports.addMatch = function(IdEquipe1, ScoreEquipe1, IdEquipe2, ScoreEquipe2, callback) {
    db.insert('matchs', { id_equipe_1 : IdEquipe1, resultat_equipe_1 : ScoreEquipe1, id_equipe_2 : IdEquipe2, resultat_equipe_2 : ScoreEquipe2 },
        callback);
};

exports.classement = function(callback) {
    db.query('SELECT * FROM equipes \
       Order by score_equipe DESC;', callback);
};

