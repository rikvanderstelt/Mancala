package nl.sogyo.mancala.domain;

import org.junit.Assert;

class Pit extends Container {

    public Pit(){
        super();
        int i = 0;
        this.addBead(4);
        owner = new Player(true);
        this.setNextContainer(new Pit(i+1,owner));
        this.getNextContainer(13).setNextContainer(this);  // Completes the circle
        this.setOpposites();
    }

    public Pit(int i, Player owner){
        super();
        this.owner = owner;
        this.addBead(4);
        if (i<5) {
            this.setNextContainer(new Pit(i + 1,owner));
        } else if (i==5) {
            this.setNextContainer(new Kalaha(owner));
        }
    }

    public void setOpposites(){
        this.opposite = this.getNextContainer(12);
        for (int i = 1; i<13; i++){
            this.getNextContainer(i).opposite =this.getNextContainer(12-i);
        }
    }

    public void emptyOpposite(){
        int beadsStolen = this.opposite.emptyPit();
        this.myKalaha().addBead(beadsStolen);
    }

    public Kalaha myKalaha(){
        int j = 0;

        for (int i=1;i<7;i++){             // Your kalaha is between 1 and 6 steps away from a pit
            if(this.getNextContainer(i) instanceof Kalaha){
                j=i;
            }
        }
        return (Kalaha) this.getNextContainer(j);
    }

    public void passBeads(int beadsPassed){
        if (beadsPassed > 1){
            this.addBead();
            this.getNextContainer().passBeads(beadsPassed-1);
        } else if (beadsPassed == 1){
            this.addBead();
            this.endTurn();
        }
    }

    public void playPit(){
        Assert.assertTrue(this.isOwnersTurn());
        int beadsPassed = this.emptyPit();
        this.getNextContainer().passBeads(beadsPassed);
    }

    public void endTurn(){
        if((this.getNumberOfBeads() == 1)&&(this.isOwnersTurn())){
            this.emptyOpposite();
            this.emptyPit();
            this.myKalaha().addBead();
        }
        this.owner.flipSelf();
        this.owner.flipOpponent();
    }

    public boolean isGameOver(){
        if(isOwnersTurn()){
            return this.gameOverCheck();
        } else{
            return this.getNextContainer(7).gameOverCheck();
        }
    }

    public boolean gameOverCheck(){
        boolean output = false;
        if(this.getNumberOfBeads() == 0){
            output = this.getNextContainer().gameOverCheck();
        }
        return output;
    }
}
