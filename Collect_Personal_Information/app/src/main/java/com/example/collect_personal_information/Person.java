package com.example.collect_personal_information;

import java.io.Serializable;

public class Person implements Serializable {
    private String id_person;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String identityCard;
    private byte[] image;
    private String music;
    private String sport;
    private String movie;
    private String pet;
    private String phoneNumber;
    private String email;
    private String homeAddress;
    private String job;
    private String position;
    private String workplaceAddress;
    private String salary;

    public Person() {

    }

    public Person(String id_person, String name, String dateOfBirth, String gender, String identityCard, byte[] image, String music, String sport, String movie, String pet, String phoneNumber, String email, String homeAddress, String job, String position, String workplaceAddress, String salary) {
        this.id_person = id_person;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.identityCard = identityCard;
        this.music = music;
        this.sport = sport;
        this.image = image;
        this.movie = movie;
        this.pet = pet;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.job = job;
        this.position = position;
        this.workplaceAddress = workplaceAddress;
        this.salary = salary;
    }

    public Person(String name, String dateOfBirth, String gender, String identityCard, byte[] image, String music, String sport, String movie, String pet, String phoneNumber, String email, String homeAddress, String job, String position, String workplaceAddress, String salary) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.identityCard = identityCard;
        this.music = music;
        this.sport = sport;
        this.image = image;
        this.movie = movie;
        this.pet = pet;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.job = job;
        this.position = position;
        this.workplaceAddress = workplaceAddress;
        this.salary = salary;
    }

    public String getId_person() {
        return id_person;
    }

    public void setId_person(String id_person) {
        this.id_person = id_person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkplaceAddress() {
        return workplaceAddress;
    }

    public void setWorkplaceAddress(String workplaceAddress) {
        this.workplaceAddress = workplaceAddress;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
