const { Client } = require('pg');

const client = new Client({
    user : 'postgres',
    host : 'localhost',
    database : 'foodgram',
    password : 'postgrespassword',
    port : 5432,
});

client.connect();

client.query('select now()', (err, res) => {
    console.log(err, res)
    client.end()
})