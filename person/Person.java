package person;

public abstract class Person {
    private String name;

    /**
     * Constructor of Person. It assigns a name to the Person object
     * @param name name of the player/enemy.
     */
    public Person (String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null) return false;
        if (!(this.getClass().equals(o.getClass()))) return false;
        Person other = (Person) o;

        if (this.getName().equals(other.getName())) return true;
        return false;
    }
}