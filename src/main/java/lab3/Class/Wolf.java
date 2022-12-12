package lab3.Class;

import javax.validation.constraints.Size;
import java.util.*;

/**
 * class Wolf for Lab1 subject Java programming technologies
 * <p>
 * @author hrrcnnmdlr
 * @version 2.0.0
 */
public class Wolf extends Animal implements Comparable<Wolf>{
    /**
     * Internal attribute: color is color of wolf
     * <p>
     * Internal attribute: parents is parents of wolf
     */
    protected String color;
    protected List<Wolf> parents;

    public Wolf(Builder builder) {
        super(builder);
        this.color = builder.color;
        this.parents = builder.parents;
    }

    /**
     * Getter method
     * @return color
     */
    public String getColor() {
        return color;
    }
    public List<Wolf> getParents() {return parents; }

    @Override
    public String toString() {
        return "\nWolf: \n"
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
     * @return true if wolves is equals or false if wolves isn't equals
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        if (this == o) return true;

        Wolf animal = (Wolf) o;

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
    public int compareTo(Wolf o) {
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
        parents.sort(Comparator.comparing(Wolf::getBirthday));
    }


    public static class Builder extends Animal.Builder{
        protected List<Wolf> parents;
        @Size(min = 3, max = 15, message = "Too short or too long color")
        protected String color;

        @Override
        public Wolf.Builder withBirthday(Calendar birthday) throws IllegalArgumentException {
            return (Wolf.Builder) super.withBirthday(birthday);
        }
        @Override
        public Wolf.Builder withName(String name) throws IllegalArgumentException{
            return (Wolf.Builder) super.withName(name);
        }
        @Override
        public Wolf.Builder withSex(String sex) throws IllegalArgumentException{
            return (Wolf.Builder) super.withSex(sex);
        }
        public Builder withColor(String color){
            if (!color.matches("^[A-Z][a-z]{0,31}"))
                throw new IllegalArgumentException("Wrong color");
            this.color = color;
            return this;
        }
        public Builder withParents(List<Wolf> parents) throws IllegalArgumentException {
            if (parents.size() != 2)
                throw new IllegalArgumentException("Animal must have 2 parents");
            this.parents = parents;
            return this;
        }
        /**
         * Build Wolf object
         *
         * @return Wolf instance
         */
        public Wolf build(){
            return new Wolf(this);
        }
    }
    /**
     * Setter method
     * @param color is color of wolf
     */
    public void setColor(String color) {
        this.color = color;
    }
    public void eraseParent(Wolf parent) {
        this.parents.remove(parent);
    }
    public void setParents(List<Wolf> parents) {
        this.parents.addAll(parents);
    }
    public void setParent(Wolf parent) {
        this.parents.add(parent);
    }

    public List<Wolf> getParentsByNameStream(String name) {
        return parents.stream().filter(wolf -> Objects.equals(wolf.getName(), name)).toList();
    }
    public List<Wolf> getParentsByName(String name) {
        List<Wolf> result = new ArrayList<>();
        for (Wolf parent : parents) {
            if (parent.getName().equals(name)) {
                result.add(parent);
            }
        }
        return result;
    }
    public List<Wolf> getParentsByAgeStream(Calendar birthday) {
        return parents.stream().filter(wolf -> Objects.equals(wolf.getBirthday(), birthday)).toList();
    }
    public List<Wolf> getParentsByAge(Calendar birthday) {
        List<Wolf> result = new ArrayList<>();
        for (Wolf parent : parents) {
            if (parent.getBirthday().equals(birthday)) {
                result.add(parent);
            }
        }
        return result;
    }
    /**
     * voice is method which print line that wolf howls
     */
    public void voice() {
        System.out.print(name);
        System.out.println(" howls");
    }

    public static void main(String... strings) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.MARCH, 5);
        Wolf animal1 = new Wolf.Builder()
                .withName("Virginia")
                .withSex("Female")
                .withBirthday(calendar)
                .withColor("grey")
                .build();
        System.out.println(animal1);
        animal1.setColor("brown");
        System.out.println(animal1);
        animal1.eat("apple");
        animal1.run();
        animal1.setName("Laura");
        animal1.voice();
    }
}