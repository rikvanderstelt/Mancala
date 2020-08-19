package nl.sogyo.mancala.domain;

abstract class Container{
    private int numberOfBeads;
    private Container nextContainer;

    public Container(){
        numberOfBeads = 0;
    }

    public Container(Container nextContainer){
        numberOfBeads = 0;
        this.nextContainer = nextContainer;
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

    public Container getNextContainer() {
        return nextContainer;
    }
}

class Pit extends Container {
    public Pit(){
        super();
        this.addBead(4);
    }
    public Pit(Container nextContainer){
        super(nextContainer);
        this.addBead(4);
    }
}

class Kalaha extends Container{

}
