package nl.sogyo.mancala.domain;

class Container{
    private int numberOfBeads;

    public Container(){
        numberOfBeads = 0;
    }

    public void addBead(){
        numberOfBeads++ ;
    }

    public void addBead(int n){
        numberOfBeads += n;
    }

    public int getNumberOfBeads(){
        return numberOfBeads;
    }
}

class Pit extends Container {
    public Pit(){
        super();
        this.addBead(4);
        }
}
