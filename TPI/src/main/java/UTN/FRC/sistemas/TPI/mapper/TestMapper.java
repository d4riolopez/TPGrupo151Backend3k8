package UTN.FRC.sistemas.TPI.mapper;

import UTN.FRC.sistemas.TPI.model.dto.TestDto;
import UTN.FRC.sistemas.TPI.model.entities.Employee;
import UTN.FRC.sistemas.TPI.model.entities.Interested;
import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.model.entities.Vehicle;
import UTN.FRC.sistemas.TPI.service.EmployeeService;
import UTN.FRC.sistemas.TPI.service.InterestedService;
import UTN.FRC.sistemas.TPI.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestMapper extends Mapper<Test, TestDto> {
    private final VehicleService vehicleService;
    private final InterestedService interestedService;
    private final EmployeeService employeeService;

    @Override
    public Test toEntity(TestDto dto) {
        //here i most valid if the test exist to load the values from db
        Test test = new Test();
        Employee employee = employeeService.findById(dto.employeeID());
        Vehicle vehicle = vehicleService.findById(dto.vehicleId());
        Interested interested = interestedService.findById(dto.interestedId());

        test.setInterested(interested);
        test.setId(dto.id());
        test.setComments(dto.comments());
        test.setEmployee(employee);
        test.setVehicle(vehicle);

        return test;
    }
}
