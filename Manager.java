import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**Class holds a graph full of Model objects (users).
 * Holds accounts and their profile pics.
 * Able to add/remove Model obj to Graph tree.
 * Has getter methods for Graph tree.
 * Able to create vertex/edges for tree.*/

public class Manager {
    //Holds all of profiles
    private Graph users;
    private ArrayList<Model> friends;
    private HashMap<String, String> accounts;
    private HashMap<String, ImageIcon> pictures;

    public Manager(){
        users = new Graph();
        friends = new ArrayList<>();
        accounts = new HashMap<>();
        pictures = new HashMap<>();
    }

    /**
     * @param name the username
     * @param password the password
     * Adds username/pw to map to store
     * */
    public void addAccount(String name, String password, String status, File file){
        if(accounts.containsKey(name))
            System.out.println("Username is already taken.");
        else
            System.out.println("Name: " + name + " is registered.");
            accounts.put(name, password);
            Model m = new Model();
            m.setName(name);
            m.setStatus(status);
            m.setImage(new File(String.valueOf(file)));
            users.addVertex(m);
            pictures.put(name, new ImageIcon(String.valueOf(file)));
    }
    /**
     * @param name the username
     * @param password the password
     * Checks if username and password is valid.
     * @return False if invalid. Else, true
     * */
    public boolean login(String name, String password){
        boolean flag = false;
        if(accounts.containsKey(name) && accounts.get(name).equals(password))
            flag = true; //The username/pass existand matches
        else if(!accounts.containsKey(name))
            System.out.println("Invalid username.");
        else if(!accounts.containsValue(password))
            System.out.println("Invalid password.");
        return flag;
    }


    public void addProfile(Model m){
        users.addVertex(m);
    }

    public void setPicture(String name, File file){
        if(pictures.containsKey(name)){
            //pictures.get(name).setImage();
        }

    }

    public ImageIcon getPicture(String name){
        ImageIcon pic = new ImageIcon("logo.png");
        if(pictures.containsKey(name)) {
            pic = pictures.get(name);
            return pic;
        }
        return pic;
    }

    /**
     * Removes all edges connected to vertex and then removes vertex
     * @param p the vertex
     * */
    public void removeProfile(Model p){
        for(Model u: users.getAdjVertices(p)){
            this.users.removeEdge(p, u);
        }

        this.users.removeVertex(p);
    }

    public int getProfilesCount(){
        System.out.println("Total Users in Graph: " + users.getVertexCount());
        return users.getVertexCount();
    }

    public ArrayList<Model> getAllUsers(String name){
        ArrayList<Model> allUsers = new ArrayList<>();
        for(Model u: users.getAllVertices())
        {
            if(!u.getName().equals(name)) //make sure it does not get our own name
                allUsers.add(u);
        }
        return allUsers;
    }

    public boolean isFriends(Model user, Model otherUser){
        return users.isConnected(user, otherUser);
    }
    public Model searchProfile(String name){
        Model v = new Model();

        if(users.getAllVertices().isEmpty()){
            System.out.println("User not found in Database Manager.");
        }
        for(Model u : users.getAllVertices()){
            if(u.getName().equals(name)){
                System.out.println("User " + name +" found!");
                return u;
            }
            System.out.println(u.getName());
        }
        return v;
    }

    public void getEdges(boolean bidirectional){
        users.getEdgesCount(bidirectional);
    }

    public void createFriendship(Model user, Model friend) {
        users.addEdge(user, friend);
    }


    /** Displays each profile's information and friends. */
    /*
    public Iterable<Database> getFriendList(Database startPoint) {
        return this.allProfiles.getNeighbors(startPoint);
    }
     */

    public ArrayList<Model> getFriendList(Model startPoint) {
        friends = this.users.getAdjVertices(startPoint);
        return friends;
    }

}