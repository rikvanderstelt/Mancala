package nl.sogyo.mancala.domain;

public class MancalaImpl implements Mancala {
    Pit firstPit;

    public MancalaImpl(String player1Name, String player2Name){
        this.firstPit = new Pit(new Player(player1Name, player2Name));
    }

    public String getPlayerName(int playerIndex) throws IllegalStateException{
        if(playerIndex == 1){
            return firstPit.getOwner().getName();
        } else if (playerIndex==2){
            return firstPit.getOwner().getOpponent().getName();
        }
        else{
            throw new IllegalStateException();
        }
    }

    public void setPlayerName(String name, int playerIndex) throws IllegalStateException{
        if(playerIndex == 1){
            firstPit.getOwner().setName(name);
        } else if (playerIndex==2){
            firstPit.getOwner().getOpponent().setName(name);
        }
        else{
            throw new IllegalStateException();
        }
    }

    public boolean isToMovePlayer(int playerIndex) throws IllegalStateException{
        if(playerIndex == 1){
            return firstPit.getOwner().isMyTurn();
        } else if (playerIndex==2){
            return firstPit.getOwner().getOpponent().isMyTurn();
        }
        else{
            throw new IllegalStateException();
        }
    }

    public int[] playRecess(int index){

        firstPit.getNextContainer(index-1).playPit();

        return this.exportGameState();
    }

    public int getStonesForPit(int index){
        assert (index>0&&index<15);
        return firstPit.getNextContainer(index-1).getNumberOfBeads();
    }

    public int[] exportGameState(){
        int[] output = new int[15];

        for(int i=0; i<6; i++){
            output[i] = this.getStonesForPit(i+1);
        }
        for(int i=7; i<13; i++){
            output[i] = this.getStonesForPit(i+1);
        }
        output[6] = this.getStonesForPit(7);
        output[13] = this.getStonesForPit(14);

        if (firstPit.getOwner().isMyTurn()){
            output[14] = 1;
        } else{
            output[14] = 2;
        }

        return output;
    }

    public boolean isEndOfGame(){
        return false;
    }

    public String getWinnersName(){
        return "Me";
    }
}
