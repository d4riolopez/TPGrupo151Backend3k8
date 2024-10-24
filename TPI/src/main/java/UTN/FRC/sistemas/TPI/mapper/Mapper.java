package UTN.FRC.sistemas.TPI.mapper;

public  abstract class Mapper<E, D> {

    public abstract E toEntity(D dto);

    //isn't necessary for now
    //public abstract D toDto(D dto);
}
