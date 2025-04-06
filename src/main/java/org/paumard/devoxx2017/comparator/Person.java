package org.paumard.devoxx2017.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;

class Person implements Comparable<Person> {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // Natural ordering based on age
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    public static Comparator<Person> NameComparator = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.age.compareTo(p2.age);
        }
    };

    public static Comparator<Person> NameComparator2 = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.name.compareTo(p2.name);
        }
    };

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        Person p1 = new Person("John", 20);
        Person p2 = new Person("Zeynep", 25);
        Person p3 = new Person("Bob", 30);
        Person p4 = new Person("Mert", 25);
        Person p5 = new Person("Alper", null);

        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);

//        personList.sort(comparing(Person::getAge)); //null pointer exception
//        System.out.println(personList);

        personList.sort(comparing(Person::getAge, nullsFirst(naturalOrder())));
        System.out.println(personList);

//        personList.sort(nullsFirst(comparing(Person::getAge)));  //null pointer exception
//        System.out.println(personList);


        personList.sort(comparing(Person::getAge, nullsFirst(naturalOrder()))
                .thenComparing(Person::getName, nullsFirst(naturalOrder())));
        System.out.println(personList);

        // System.out.println("Natural ordering based on age");
        // System.out.println(p1.compareTo(p2)); // -1
        // System.out.println(p2.compareTo(p3)); // -1
        // System.out.println(p3.compareTo(p4)); // 1
        // System.out.println(p1.compareTo(p4)); // -1

        // System.out.println("Comparator based on name");
        // System.out.println(Person.NameComparator.compare(p1, p2)); // 1
        // System.out.println(Person.NameComparator.compare(p2, p3)); // -1
        // System.out.println(Person.NameComparator.compare(p3, p4)); // 1
        // System.out.println(Person.NameComparator.compare(p1, p4)); // 0

        // System.out.println("Comparator based on name and then age");
        Comparator<Person> cmp = Person.NameComparator.thenComparing(Person::compareTo);
        // System.out.println(cmp.compare(p1, p2)); // -1
        // System.out.println(cmp.compare(p2, p3)); // -1
        // System.out.println(cmp.compare(p3, p4)); // 1
        // System.out.println(cmp.compare(p1, p4)); // -1

        // System.out.println("Comparator based on name and then age with nulls first");
        Comparator<Person> cmp2 = nullsFirst(Person.NameComparator).thenComparing(Person::compareTo);
        // System.out.println(cmp2.compare(p1, p2)); // -1
        // System.out.println(cmp2.compare(p2, p3)); // -1
        // System.out.println(cmp2.compare(p3, p4)); // 1
        // System.out.println(cmp2.compare(p1, p4)); // -1
    }


}

