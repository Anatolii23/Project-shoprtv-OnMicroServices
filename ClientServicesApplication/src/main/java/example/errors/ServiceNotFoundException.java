package example.errors;

public class ServiceNotFoundException extends RuntimeException{
    public ServiceNotFoundException(Long id){
        super("Could not find object which id = " + id);
    }
}
