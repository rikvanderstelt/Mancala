package nl.sogyo.mancala.domain;

class Kalaha extends Container{

    public Kalaha(Player owner){
        super(owner);
    }



    public Container getOpposite(){
        return this.getNextContainer(7);
    }

    public Container getOpposite(int pitsBeforeKalaha) {
        return this.getNextContainer(pitsBeforeKalaha);
    }

    public void playPit()  {
        System.out.println("Error: Tried playing a kalaha");
    }

    public void  passBeads(int beadsPassed){
        if (isOwnersTurn()){
            super.passBeads(beadsPassed);
        } else {
            this.getNextContainer().passBeads(beadsPassed);
        }
    }

    public void stealFromOpposite(){}
    public void endTurn(){}

    public Kalaha findMyKalaha(){
        return this;
    }

    public boolean isGameOver(){
        if (this.isOwnersTurn()){
            return this.getNextContainer(8).allPitsEmptyCheck();
        } else {
            return this.getNextContainer(1).allPitsEmptyCheck();
        }
    }

    public boolean allPitsEmptyCheck(){
        return true;
    }
}