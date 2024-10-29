package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.EmployeeAlreadyTestingException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Interested.InterestedIsRestrictedException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Interested.InterestedLicenseExpiredException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.TestNotFoundException;
import UTN.FRC.sistemas.TPI.exceptionHandling.exception.Vehicle.VehicleAlreadyTestingException;
import UTN.FRC.sistemas.TPI.model.dto.TestDto;
import UTN.FRC.sistemas.TPI.model.entities.Employee;
import UTN.FRC.sistemas.TPI.model.entities.Interested;
import UTN.FRC.sistemas.TPI.model.entities.Test;
import UTN.FRC.sistemas.TPI.model.entities.Vehicle;
import UTN.FRC.sistemas.TPI.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService extends ServiceImp<Test, Long> {
    private final TestRepository repository;
    private final String notFoundMessage = "There isn't a test with that id:";

    @Override
    public void create(Test test) {
        validateEmployee(test.getEmployee());
        validateInterested(test.getInterested());
        validateVehicle(test.getVehicle());
        test.setStartedDateTime(LocalDateTime.now());

        repository.save(test);
    }

    @Override
    public Test findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(notFoundMessage + id));
    }

    @Override
    public List<Test> getAll() {
        return repository.findAll();
    }

    @Override
    public Test update(Test entity) {
        if (existsById(entity.getId())) {
            return repository.save(entity);
        } else {
            throw new TestNotFoundException(notFoundMessage + entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        Test test = findById(id);
        repository.delete(test);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void finishTest(Test test) {
        test.setEndedDateTime(LocalDateTime.now());
        test.getVehicle().setOnTesting(false);
        test.getEmployee().setOnTesting(false);
        update(test);
    }

    private void validateEmployee(Employee employee) {
        if (employee.isOnTesting())
            throw new EmployeeAlreadyTestingException("The current employee is in a drive test already");
    }

    private void validateInterested(Interested interested) {
        if (!interested.isLicenceActive())
            throw new InterestedLicenseExpiredException("The license of the interested party has expired");
        if (interested.getRestricted())
            throw new InterestedIsRestrictedException("The current interested is restricted in the company");
    }

    private void validateVehicle(Vehicle vehicle) {
        if (vehicle.isOnTesting())
            throw new VehicleAlreadyTestingException("The current vehicle is in a drive test already");
    }

    public List<Test> getOngoingTests() {
        return repository.findByEndedDateTimeIsNull();
    }
}
