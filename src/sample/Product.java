package sample;

public class Product {
    private int id;
    private String directory;
    private String executable;
    private double running;

    public Product() {
        this.id = id;
        this.directory = directory;
        this.executable = executable;
        this.running = running;
    }

    public Product(int id, String directory, String executable, double running) {
        this.id = id;
        this.directory = directory;
        this.executable = executable;
        this.running = running;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public double getRunning() {
        return running;
    }

    public void setRunning(double running) {
        this.running = running;
    }
}
