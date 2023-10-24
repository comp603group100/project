/**
 * @author group100 (19094184, 19088716)
 * @author baeldung.com (the listFiles() function)
 */
//We're using serialization here because it's far more efficient than bufferedreader/writer.
package virtualpetgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO {

    private static final String SAVE_FOLDER = "./saves/";
    private static final String EXTENSION = ".save";

    private final Scanner keyboard = new Scanner(System.in);

    private String fileName;

    private ActivePet activePet;
    private final GameDataManager gdm;

    private final List<String> saves;

    /**
     * SaveLoadSystem constructor. Creates a list of saves available on
     * construction.
     *
     * @param gdm : the GameDataManager object used to store the currently
     * active filename, or the previously played state.
     */
    public FileIO(GameDataManager gdm) {
        this.gdm = gdm;

        //If the directories don't exist, create them.
        //This is safe to call without an if statement.
        new File(SAVE_FOLDER).mkdirs();

        this.saves = new ArrayList<>(listFiles(SAVE_FOLDER));

        //We need to loop over the list twice here
        //this is because saves size can change when files are removed.
        for (int i = 0; i < saves.size(); i++) {
            //If MacOS .DS_Store files are found, remove them
            if (saves.get(i).equals(".DS_Store")) {
                saves.remove(i);
            }
        }
        for (int i = 0; i < saves.size(); i++) {
            saves.set(i, saves.get(i).replace(EXTENSION, "")); //remove the extensions
        }

        /*
        Game state can be corrupted by manually deleting files.
        This will ensure that when no saves exist, or the previous save is deleted, 
        that there's no game available for continuing.
         */
        if (!this.savesExist() || !this.saves.contains(this.gdm.getPreviousGame())) {
            this.gdm.writePreviousGame("");
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ActivePet getActivePet() {
        return activePet;
    }

    public void setActivePet(ActivePet activePet) {
        this.activePet = activePet;
    }

    public boolean fileExists(String s) {
        for (String f : saves) {
            if (s.equals(f)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Prompts the user to choose a save file from a list of available files.
     *
     * @return a String with the name of the chosen save file, not including the
     * file extension.
     */
    public String chooseFile() {

        int max = 0;

        System.out.println("Enter the number of the save you want to load:");

        for (int i = 0; i < saves.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + saves.get(i) + " (" + getUnloadedPetType(saves.get(i)) + ")");
            max = i;
        }

        boolean choosing = true;
        int choice = 0;

        while (choosing) {
            try {
                choice = keyboard.nextInt() - 1;
                keyboard.nextLine();

                if (choice > max && choice > 0) {
                    System.out.println("Please enter a number corresponding to the save you want to load.");
                } else {
                    choosing = false;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a number corresponding to the save you want to load.");
                keyboard.next();
            }
        }

        return saves.get(choice); //get the string from the arraylist.
    }

    /**
     * Checks if any saves exist. Final, cannot be overridden.
     *
     * @return true if saves exist, false otherwise
     */
    public final boolean savesExist() {
        return !this.saves.isEmpty();
    }

    //From https://www.baeldung.com/java-list-directory-files
    /**
     * lists all files in a directory and returns a set of those files
     *
     * @param dir the directory to list
     * @return a set of strings, of the filenames of the saves.
     */
    private Set<String> listFiles(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    /**
     * saves the file to a file called <this.fileName>.save
     */
    public synchronized void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(SAVE_FOLDER + this.fileName + EXTENSION);
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(this.activePet);
                this.gdm.writePreviousGame(this.fileName);
            }

        } catch (java.io.IOException e) {
            System.err.print(e);
        }
    }

    /**
     * Loads the file stored in <this.fileName>
     *
     * @return an ActivePet object loaded from the stored file. or returns null
     * if an ActivePet wasn't loaded.
     */
    public synchronized ActivePet load() {
        try {
            ActivePet loadedObject;
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SAVE_FOLDER + this.fileName + EXTENSION))) {
                loadedObject = (ActivePet) objectInputStream.readObject();
            }

            this.activePet = loadedObject;

            return loadedObject;
        } catch (IOException | ClassNotFoundException e) {
            System.err.print(e);
        }

        return null;
    }

    /**
     * Returns the name of the petType stored in the checked file.
     *
     * @param fileToCheck the fileName to check
     * @return a string containing the name of the petType
     */
    public String getUnloadedPetType(String fileToCheck) {
        try {
            ActivePet loadedObject;
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SAVE_FOLDER + fileToCheck + EXTENSION))) {
                loadedObject = (ActivePet) objectInputStream.readObject();
            }

            return loadedObject.getPetType();
        } catch (IOException | ClassNotFoundException e) {
            System.err.print(e);
        }

        return null;
    }

    /**
     * Deletes the current save file, and removes it from the previous game
     * file.
     */
    public void delete() {
        this.gdm.writePreviousGame("");
        File file = new File(SAVE_FOLDER + this.fileName + EXTENSION);
        file.delete();
    }
}
