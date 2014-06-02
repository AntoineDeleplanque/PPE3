// init some framework/library/dependency
var data = require('./ortoba-data.js');
var express = require('express');
var app = express();

// function who see if it has any probleme and send to customer data or error message.
function dataCallback(res){
    return function(err, data){
        if(err){
            res.send({error : err});
        }else{
            res.send(data);
        }
    };
}

// server/equipe/getAll return all equipe in db order (no classement, just id order)
app.get('/equipe/getAll',function(req, res){
    data.getEquipes(dataCallback(res));
});

// server/equipe/classement return all equipe in order of his score
app.get('/equipe/classement', function(req, res){
    data.classement(dataCallback(res));
});

// server/equipe/add/{nameEquipe}/{nameVille} insert into database a new equipe
app.post('/equipe/add/:nom/:ville', function(req, res){
    console.log("addinscr");
    data.addEquipe(req.params.nom, req.params.ville, dataCallback(res));
});

// server/match/add/{nameEquipe1}/{scoreEquipe1}/{nameEquipe2}/{scoreEquipe2} insert into database et new match with score
app.post('/match/add/:IdEquipe1/:ScoreEquipe1/:IdEquipe2/:ScoreEquipe2', function(req, res){
    console.log("addmatch");
    data.addMatch(req.params.IdEquipe1, req.params.ScoreEquipe1, req.params.IdEquipe2, req.params.ScoreEquipe2, dataCallback(res));
});

// the web service listence (work) on port 80
app.listen(80);