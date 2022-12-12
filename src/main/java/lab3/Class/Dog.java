package lab3.Class;


import javax.validation.constraints.Size;
import java.util.*;

/**
 * class Dog for Lab1 subject Java programming technologies
 * <p>
 * @author hrrcnnmdlr
 * @version 2.0.0
 */
public class Dog extends Wolf implements Comparable<Wolf>{
    /**
     * Internal attribute: isMongrel is a variable that indicates whether the dog is a mongrel
     * <p>
     * Internal attribute: parents is parents of dog
     */
    protected boolean isMongrel;
    protected List<Dog> parents;

    public Dog(Dog.Builder builder) {
        super(builder);
        this.isMongrel = builder.isMongrel;
        this.parents = builder.parents;

    }
    /**
     * Getter method
     * @return isMongrel
     */
    public Boolean isMongrel() {
        return isMongrel;
    }
    public List<Dog> getDogParents() {return parents; }
    @Override
    public String toString() {
        return "\nDog: \n"
                + "    Name: " + name + '\n'
                + "    Sex: " + sex + '\n'
                + "    Birthday: "  +  birthday.get(Calendar.DATE) + "."
                + birthday.get(Calendar.MONTH) + "." + birthday.get(Calendar.YEAR) + " y.o\n"
                + "    Color: " + color + '\n'
                + "    Dog is a mongrel: " + isMongrel + '\n'
                + "    Parents: " + parents + '\n';
    }
    /**
     * equals is override method for class Wolf
     * <p>
     * @param o is object that is compared to the current object
     * @return true if dogs is equals or false if dogs isn't equals
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        if (this == o) return true;

        Dog animal = (Dog) o;

        if (!getBirthday().equals(animal.getBirthday())) {
            return false;
        } else if (!getSex().equals(animal.getSex())) {
            return false;
        } else if (!getColor().equals(animal.getColor())) {
            return false;
        } else if (!isMongrel().equals(animal.isMongrel())) {
            return false;
        } else if (!getDogParents().equals(animal.getDogParents())) {
            return false;
        } else return getName().equals(animal.getName());
    }

    public int compareTo(Dog o) {
        return (this.name).compareTo(o.name);
    }

    public void sortDogParentsByName() {
        Collections.sort(parents);
    }

    public void sortDogParentsByAge() {
        parents.sort((o1, o2) -> {
            if (o1.getBirthday().before(o2.getBirthday()))
                return -1;
            else if (o1.getBirthday().equals(o2.getBirthday()))
                return 0;
            return 1;
        });
    }

    public void sortDogParentsByAgeComparator() {
        parents.sort(Comparator.comparing(Dog::getBirthday));
    }


    public static class Builder extends Wolf.Builder{
        protected List<Dog> parents;
        protected boolean isMongrel;
        @Override
        public Builder withBirthday(Calendar birthday) throws IllegalArgumentException {
            return (Builder) super.withBirthday(birthday);
        }
        @Override
        public Dog.Builder withName(String name) throws IllegalArgumentException{
            return (Builder) super.withName(name);
        }
        @Override
        public Dog.Builder withSex(String sex) throws IllegalArgumentException{
            return (Builder) super.withSex(sex);
        }
        @Override
        public Dog.Builder withColor(String color){
            return (Builder) super.withColor(color);
        }
        public Dog.Builder withDogParents(List<Dog> parents) {
            this.parents = parents;
            return this;
        }
        public Dog.Builder isMongrel(Boolean isMongrel){
            this.isMongrel = isMongrel;
            return this;
        }
        /**
         * Build Dog object
         *
         * @return Dog instance
         */
        public Dog build(){
            return new Dog(this);
        }
    }
    /**
     * Setter method
     * @param isMongrel is a variable that indicates whether the dog is a mongrel
     */
    public void changeMongrel(Boolean isMongrel) {
        this.isMongrel = isMongrel;
    }
    public void eraseDogParent(Dog parent) {
        this.parents.remove(parent);
    }
    public void setDogParents(List<Dog> parents) {
        this.parents.addAll(parents);
    }
    public void setDogParent(Dog parent) {
        this.parents.add(parent);
    }

    public List<Dog> getDogParentsByNameStream(String name) {
        return parents.stream().filter(dog -> Objects.equals(dog.getName(), name)).toList();
    }
    public List<Dog> getDogParentsByName(String name) {
        List<Dog> result = new ArrayList<>();
        for (Dog parent : parents) {
            if (parent.getName().equals(name)) {
                result.add(parent);
            }
        }
        return result;
    }
    public List<Dog> getDogParentsByAgeStream(Calendar birthday) {
        return parents.stream().filter(dog -> Objects.equals(dog.getBirthday(), birthday)).toList();
    }
    public List<Dog> getDogParentsByAge(Calendar birthday) {
        List<Dog> result = new ArrayList<>();
        for (Dog parent : parents) {
            if (parent.getBirthday().equals(birthday)) {
                result.add(parent);
            }
        }
        return result;
    }

    /**
     * voice is override method which print line that dog howls and barks
     */
    @Override
    public void voice() {
        super.voice();
        System.out.print(name);
        System.out.println(" barks");
    }

    public static void main(String... strings) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.MARCH, 5);
        Dog animal1 = new Dog.Builder()
                .withName("Virginia")
                .withSex("Female")
                .withBirthday(calendar)
                .withColor("grey")
                .isMongrel(false)
                .build();
        System.out.println(animal1);
        animal1.changeMongrel(true);
        System.out.println(animal1);
        animal1.eat("apple");
        animal1.run();
        animal1.setName("Laura");
        animal1.voice();
    }
}
