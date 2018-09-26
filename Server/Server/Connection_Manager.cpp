//
//  Connection_Manager.cpp
//  Server2
//
//  Created by intx on 23.09.18.
//  Copyright © 2018 intx. All rights reserved.
//

#include "Connection_Manager.hpp"

Connection_Manager::Connection_Manager(){
    freeGame.reset();
}

Connection_Manager::~Connection_Manager(){
}

void Connection_Manager::add_Connection(Connection::ptr_Connection connection){
    
    auto self(shared_from_this());
    if (connection){
        connection->addConnectionManager(self);
        connection->start();
        this->connections.push_back(connection);
    }
    else throw std::runtime_error("Given connection is not Accesable");
}

void Connection_Manager::erase_Connection(Connection::ptr_Connection connection){
    
}

void Connection_Manager::connectToGame(Connection::ptr_Connection connection) {
    if(connection){
        if(!freeGame){
            //Create New Game
            Game::ptr_Game newGame = Game::create();
            if(newGame->addPlayer(connection)){
                freeGame = std::move(newGame);
                connection->addGame(freeGame);
                mmap_.insert(std::pair<Game::ptr_Game, Connection::ptr_Connection>(newGame, connection));
            }
            else throw std::runtime_error("Connection_Manager::Could not add Connection to new Game");
        }
        else {
            //insert into existing
            if(freeGame->addPlayer(connection)){
                mmap_.insert(std::pair<Game::ptr_Game, Connection::ptr_Connection>(freeGame, connection));
                connection->addGame(freeGame);
                if(freeGame->getAmountPlayer() == MaxPlayer){
                    freeGame.reset();
                }
            }
            else throw std::runtime_error("Connection_Manager::Could not add Connection to existing Game");
        }
    }
    else{
        throw std::runtime_error("Connection_Manager::add_Connection::Connection not lequid anymore");
    }
}

void Connection_Manager::handle_Request(Connection::ptr_Connection connection, std::string request){
    std::stringstream ss(request);
    std::string method, para1, para2, para3 = "";
    std::string instr[20];
    
    while(!ss.eof()){
        ss >> instr[0];
        std::cout << "METHOD " << instr[0] << std::endl;
        if(instr[0] == "LOGIN"){
            //Create new Account
            instr[1] = instr[0];
            ss >> instr[0];
            ss >> instr[0];
            if(instr[0] == "name"){
                instr[2] = instr[0];
                ss >> instr[0];
                if (instr[0] == "password"){
                    instr[3] = instr[0];
                    Account acc{instr[2], instr[3], true};
                    connection->connectToAccount(acc);
                }
            }
            this->connectToGame(connection);
        }
        else if (method == "TURN"){
            
        }
        else if (method == "GAMEDATA"){
            
        }
        else {
            std::cout << "COULD NOT PARSE" << std::endl;
            return;
        }
    }
    
}
