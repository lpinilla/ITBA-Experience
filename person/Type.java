package person;

public class Type {
    private String name;

    /**
     * Creates a new  Type
     * @param name
     */
    public Type(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    /**
     * A Type equals another iff they have the same name (and are the same type)
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if ( this == o) return true;
        if (o == null) return false;
        if (!this.getClass().equals(o.getClass())) return false;

        Type other = (Type) o;
        if (this.getName().equals(other.getName())) return true;
        return false;
    }
}