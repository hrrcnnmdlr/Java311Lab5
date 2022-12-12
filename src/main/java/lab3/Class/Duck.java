package lab3.Class;

import javax.validation.constraints.Size;
import java.util.*;

/**
 * class Duck for Lab1 subject Java programming technologies
 * <p>
 * @author hrrcnnmdlr
 * @version 2.0.0
 */
public class Duck extends Animal implements Comparable<Duck>{
    /**
     * Internal attribute: color is color of duck
     * <p>
     * Internal attribute: parents is parents of duck
     */
    protected String color;
    protected List<Duck> parents;

    public Duck(Duck.Builder builder) {
        super(builder);
        this.color = builder.color;
        this.parents = builder.parents;
    }

    /**
     * Getter method
     * @return color
     */
    private String getColor() {
        return color;
    }
    private List<Duck> getParents() {return parents; }

    @Override
    public String toString() {
        return "\nDuck: \n"
                + "    Name: " + name + '\n'
                + "    Sex: " + sex + '\n'
                + "    Birthday: "  +  birthday.get(Calendar.DATE) + "."
                + birthday.get(Calendar.MONTH) + "." + birthday.get(Calendar.YEAR) + " y.o\n"
                + "    Color: " + color + '\n'
                + "    Parents: " + parents + '\n';
    }
    /**
     * equals is override method for class Animals
     * <p>
     * @param o is object that is compared to the current object
     * @return true if ducks is equals or false if ducks isn't equals
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        if (this == o) return true;

        Duck animal = (Duck) o;

        if (!getBirthday().equals(animal.getBirthday())) {
            return false;
        } else if (!getSex().equals(animal.getSex())) {
            return false;
        } else if (!getColor().equals(animal.getColor())) {
            return false;
        } else if (!getParents().equals(animal.getParents())) {
            return false;
        } else return getName().equals(animal.getName());
    }

    @Override
    public int compareTo(Duck o) {
        return (this.name).compareTo(o.name);
    }

    public void sortParentsByName() {
        Collections.sort(parents);
    }

    public void sortParentsByAge() {
        parents.sort((o1, o2) -> {
            if (o1.getBirthday().before(o2.getBirthday()))
                return -1;
            else if (o1.getBirthday().equals(o2.getBirthday()))
                return 0;
            return 1;
        });
    }

    public void sortParentsByAgeComparator() {
        parents.sort(Comparator.comparing(Duck::getBirthday));
    }


    public static class Builder extends Animal.Builder{
        protected List<Duck> parents;
        @Size(min = 3, max = 15, message = "Too short or too long color")
        protected String color;
        @Override
        public Duck.Builder withBirthday(Calendar birthday) throws IllegalArgumentException {
            return (Duck.Builder) super.withBirthday(birthday);
        }
        @Override
        public Duck.Builder withName(String name) throws IllegalArgumentException{
            return (Duck.Builder) super.withName(name);
        }
        @Override
        public Duck.Builder withSex(String sex) throws IllegalArgumentException{
            return (Duck.Builder) super.withSex(sex);
        }

        public Duck.Builder withColor(String color) {
            if (!color.matches("^[A-z]{0,31}"))
                throw new IllegalArgumentException("Wrong color");
            this.color = color;
            return this;
        }

        public Duck.Builder withParents(List<Duck> parents) {
            if (parents.size() != 2)
                throw new IllegalArgumentException("Animal must have 2 parents");
            this.parents = parents;
            return this;
        }
        /**
         * Build Duck object
         *
         * @return Duck instance
         */
        public Duck build(){
            return new Duck(this);
        }
    }
    /**
     * Setter method
     * @param color is color of duck
     */
    public void setColor(String color) {
        this.color = color;
    }
    public void eraseParent(Duck parent) {
        this.parents.remove(parent);
    }
    public void setParents(List<Duck> parents) {
        this.parents.addAll(parents);
    }
    public void setParent(Duck parent) {
        this.parents.add(parent);
    }

    public List<Duck> getParentsByNameStream(String name) {
        return parents.stream().filter(duck -> Objects.equals(duck.getName(), name)).toList();
    }
    public List<Duck> getParentsByName(String name) {
        List<Duck> result = new ArrayList<>();
        for (Duck parent : parents) {
            if (parent.getName().equals(name)) {
                result.add(parent);
            }
        }
        return result;
    }
    public List<Duck> getParentsByAgeStream(Calendar birthday) {
        return parents.stream().filter(duck -> Objects.equals(duck.getBirthday(), birthday)).toList();
    }
    public List<Duck> getParentsByAge(Calendar birthday) {
        List<Duck> result = new ArrayList<>();
        for (Duck parent : parents) {
            if (parent.getBirthday().equals(birthday)) {
                result.add(parent);
            }
        }
        return result;
    }
    /**
     * voice is method which print line that duck croaks
     */
    public void voice() {
        System.out.print(name);
        System.out.println(" croaks");
    }

    public static void main(String... strings) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.MARCH, 5);
        Duck animal1 = new Duck.Builder()
                .withName("Donald")
                .withSex("Male")
                .withBirthday(calendar)
                .withColor("grey")
                .build();
        System.out.println(animal1);
        animal1.setColor("brown");
        System.out.println(animal1);
        animal1.eat("apple");
        animal1.run();
        animal1.setName("Mario");
        animal1.voice();
    }
}
