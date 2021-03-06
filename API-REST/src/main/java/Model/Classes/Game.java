package Model.Classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Game {

    private int Id;
    private int nPlayers;
    private User PlayerTurn;
    private List<User> Players = new ArrayList<>();
    private String Winner;
    private String timeStamp ;
    private Board Board;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getnPlayers() {
        return nPlayers;
    }

    public void setnPlayers(int nPlayers) {
        this.nPlayers = nPlayers;
    }

    public User getPlayerTurn() {
        return PlayerTurn;
    }

    public void setPlayerTurn(User playerTurn) {
        PlayerTurn = playerTurn;
    }

    public List<User> getPlayers() {
        return Players;
    }

    public void setPlayers(List<User> players) {
        Players = players;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Model.Classes.Board getBoard() {
        return Board;
    }

    public void setBoard(Model.Classes.Board board) {
        Board = board;
    }


    public Game( List<User> Players){
        this.Players= Players;
        this.nPlayers= Players.size();
        this.timeStamp = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(Calendar.getInstance().getTime());
        this.Board = new Board(Players);
        this.PlayerTurn=this.Players.get(0);
    }

    public boolean Move(char Direction , User player ){
        boolean moved= false;
        //if (CheckMovementPosible(Direction , player)){
        while (moved==false) {
            switch (Direction) {
                case 'S':
                    this.Board.MoveSouth(player);
                    moved=true;
                    if (!isGameWon())
                        NextTurn();
                    break;
                case 'N':
                    this.Board.MoveNorth(player);
                    moved=true;
                    if (!isGameWon())
                        NextTurn();
                    break;
                case 'E':
                    this.Board.MoveEast(player);
                    moved=true;
                    if (!isGameWon())
                        NextTurn();
                    break;
                case 'W':
                    this.Board.MoveWest(player);
                    moved=true;
                    if (!isGameWon())
                        NextTurn();
                    break;
            }
        }
        //}
        return moved;
    }
    private boolean isGameWon(){
     Cell EndCell=this.Board.getWinningCell();
     Cell CurrentCell=this.Board.getPlayerPosition(this.PlayerTurn); //Esto no se actualiza
     if (EndCell==CurrentCell){
         this.Winner=PlayerTurn.getEmail();
         for (User player: this.Players) {
             player.setGamesPlayed(player.getGamesPlayed()+1);
         }
         this.PlayerTurn.setGamesWon( this.PlayerTurn.getGamesWon()+1);
         System.out.println("Player "+this.PlayerTurn.getUserName() + " has won the game.");
         return true;
     }
     else {
         return false;
     }
    }

    /*private boolean CheckMovementPosible(char Direction , User player ){
        if (this.Board.checkPosibleMoves(player) !=null){
            if (this.Board.checkPosibleMoves(player).contains(Direction) && this.PlayerTurn==player){
                return true;
            }
        }
        return false;
    }*/

    private void NextTurn(){
        for(int i=0; i< this.Players.size(); i++){
            if (this.Players.get(i) == this.PlayerTurn){
                if (i==this.Players.size()-1) {
                    this.PlayerTurn = this.Players.get(0);
                    return;
                }
                else{
                    this.PlayerTurn=this.Players.get(i+1);
                    return;
                }
            }
        }
    }
    @Override
    public String toString() {
        return ( this.timeStamp + "     Winner: " + this.Winner + "   " + this.nPlayers+ " players.\n");
    }

    public Game(int id, List<User> players, String winner, String timeStamp) {
        Id = id;
        Players = players;
        nPlayers=players.size();
        Winner = winner;
        this.timeStamp = timeStamp;
    }

    public Game(){}

}
