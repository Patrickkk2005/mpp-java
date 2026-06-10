package eu.ase.claudeex;

public class GamingLaptop extends Laptop {
    private String gpuModel;
    public GamingLaptop() {
        super();
    }
    public String getGpuModel() {
        return gpuModel;
    }
    public void setGpuModel(String gpuModel) {
        if(gpuModel != null && gpuModel.length() > 1) {
            this.gpuModel = gpuModel;
        }
    }
    @Override
    public String getInfo(){
        return gpuModel;
    }
}
