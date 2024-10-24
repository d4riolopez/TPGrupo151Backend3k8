package UTN.FRC.sistemas.TPI.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TestDto(Long id,
                      @Size(min = 3, message = "the comment needs to have at least 3 characters")
                      String comments,
                      @NotNull(message = "Employee id must be included")
                      Long employeeID,
                      @NotNull(message = "Interested id must be included")
                      Long interestedId,
                      @NotNull(message = "Vehicle id can't be null")
                      Long vehicleId) {
}
