package lab3.Class;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Objects;

/**
 * class Animal for Lab1 subject Java programming technologies
 * <p>
 * @author hrrcnnmdlr
 * @version 2.0.0
 */
public class Animal{
    /**
     * Internal attribute: name is name of animal
     * <p>
     * Internal attribute: sex is sex of animal
     * <p>
     * Internal attribute: birthday  is age of animal
     * <p>
     * Internal attribute: parents is parents of animal
     */
    protected String name;
    protected String sex;
    protected final Calendar birthday;

    public Animal(Builder builder) {
        this.name = builder.name;
        this.sex = builder.sex;
        this.birthday = builder.birthday;
    }

    /**
     * Getter method
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Getter method
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Getter method
     *
     * @return birthday
     */
    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "\nAnimal: \n"
                + "    Name: " + name + '\n'
                + "    Sex: " + sex + '\n'
                + "    Birthday: "  +  birthday.get(Calendar.DATE) + "."
                + birthday.get(Calendar.MONTH) + "." + birthday.get(Calendar.YEAR) + " y.o\n";
    }
    /**
     * equals is override method for class Object
     * <p>
     * @param o is object that is compared to the current object
     * @return true if animals is equals or false if animals isn't equals
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        if (this == o) return true;

        Animal animal = (Animal) o;

        if (!getBirthday().equals(animal.getBirthday())) {
            return false;
        } else if (!getSex().equals(animal.getSex())) {
            return false;
        } else return getName().equals(animal.getName());
    }
    /**
     * hashCode is override method for class Object
     * <p>
     * @return hashCode that is defined from length of name multiply by age
     */
    @Override
    public int hashCode() {
        int number = 0;
        if (!Objects.equals(sex, "male")) {number = name.length();};
        return name.length() * number;
    }
    public static class Builder{
        @Size(min = 1, max = 32, message = "Too short or too long name")
        protected String name;
        @PastOrPresent(message = "Animal must be born")
        protected Calendar birthday;
        @Size(min = 4, max = 6, message = "Incorrect sex")
        protected String sex;
        /**
         * Set name
         *
         * @param name is name of animal
         * @return Builder instance
         */
        public Builder withName(String name) throws IllegalArgumentException {
            if (!name.matches("^[A-Z][a-z]{0,31}"))
                throw new IllegalArgumentException("Wrong name");
            this.name = name;
            return this;
        }
        /**
         * Check date and set it as day of human birth
         *
         * @param birthday day of animal birth
         * @return Builder instance
         * @throws IllegalArgumentException if year of animal birth less than 1850
         */
        public Builder withBirthday(Calendar birthday) throws IllegalArgumentException {
            if (birthday.get(Calendar.YEAR) < 1950)
                throw new IllegalArgumentException("Wrong birthday. Human too old");
            if (Calendar.getInstance().compareTo(birthday) < 0)
                throw new IllegalArgumentException("Wrong birthday. Human too young");
            this.birthday = birthday;
            return this;
        }
        /**
         * Set sex
         *
         * @param sex is sex of animal
         * @return Builder instance
         */
        public Builder withSex(String sex) throws IllegalArgumentException {
            if (!sex.equals("female") && !sex.equals("male"))
                throw new IllegalArgumentException("Incorrect sex. Enter 'female' or 'male'.");
            this.sex = sex;
            return this;
        }
        /**
         * Build Animal object
         *
         * @return Animal instance
         */
        public Animal build(){
            return new Animal(this);
        }

    }
    /**
     * Setter method
     * @param name is name of animal
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Setter method
     * @param sex is sex of animal
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * eat is method which print line that animal eat food
     * @param food is food which animal eat
     */
    public void eat(String food) {
        System.out.print(name);
        System.out.print(" eats ");
        System.out.println(food);
    }
    /**
     * sleep is method which print line that animal sleep
     */
    public void sleep() {
        System.out.print(name);
        System.out.println(" sleeps");
    }
    /**
     * run is method which print line that animal runs
     */
    public void run() {
        System.out.print(name);
        System.out.println(" runs");
    }

    public static void main(String... strings) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.MARCH, 5);
        Animal animal1 = new Animal.Builder()
                .withName("Donald")
                .withSex("Male")
                .withBirthday(calendar)
                .build();
        Animal animal2 = new Animal.Builder()
                .withName("Donald")
                .withSex("Male")
                .withBirthday(calendar)
                .build();
        Animal animal3 = new Animal.Builder()
                .withName("Virginia")
                .withSex("Female")
                .withBirthday(calendar)
                .build();
        System.out.println("Is animal2 equals animal1? " + animal1.equals(animal2));
        System.out.println(animal1);
        System.out.println(animal2);
        System.out.println("Is animal2 equals animal1? " + animal1.equals(animal2));
        System.out.println(animal1);
        System.out.println(animal2);
        System.out.println("Is animal3 equals animal1? " + animal1.equals(animal3));
        System.out.println(animal1);
        System.out.println(animal3);
        animal3.setSex("Male");
        System.out.println(animal3);
        animal1.eat("apple");
        animal2.run();
        animal2.setName("Mario");
        animal2.eat("apple");
        animal3.sleep();
    }
}


