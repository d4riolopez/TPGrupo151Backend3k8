package UTN.FRC.sistemas.TPI.service;

import UTN.FRC.sistemas.TPI.exceptionHandling.exception.EmployeeNotFoundException;
import UTN.FRC.sistemas.TPI.model.entities.Employee;
import UTN.FRC.sistemas.TPI.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService extends ServiceImp<Employee, Long> {
    private final EmployeeRepository employeeRepository;

    private final String notFoundMessage = "There isn't an employee with that id:";

    @Override
    public void create(Employee entity) {
        employeeRepository.save(entity);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(notFoundMessage + id));
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee entity) {
        if(existsById(entity.getFile())){
            return employeeRepository.save(entity);
        }else{
            throw new EmployeeNotFoundException(notFoundMessage + entity.getFile());
        }
    }

    @Override
    public void delete(Long id) {
        employeeRepository.delete(findById(id));
    }

    @Override
    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }
}
