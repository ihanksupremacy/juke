package Juke.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Juke.entity.Employee;
import Juke.repository.EmployeeRepository;
import Juke.dto.SuccessResponse;
import Juke.dto.ErrorResponse;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public ResponseEntity<?> getAll() {
        try {
            List<Employee> employees = repo.findAll();
            return ResponseEntity.ok(new SuccessResponse<>(employees));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ErrorResponse("Internal Server Error"));
        }
    }

    public ResponseEntity<?> getById(UUID id) {
        try {
            Employee employee = repo.findById(id).orElse(null);

            if (employee == null) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponse("Employee not found"));
            }

            return ResponseEntity.ok(new SuccessResponse<>(employee));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ErrorResponse("Internal Server Error"));
        }
    }

    public ResponseEntity<?> create(Employee employee) {
        try {
            if (repo.existsByEmail(employee.getEmail())) {
                return ResponseEntity.status(400)
                        .body(new ErrorResponse("Email sudah digunakan!"));
            }

            Employee saved = repo.save(employee);
            return ResponseEntity.status(201)
                    .body(new SuccessResponse<>(saved));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ErrorResponse("Internal Server Error"));
        }
    }

    public ResponseEntity<?> update(UUID id, Employee employee) {
        try {
            Employee existing = repo.findById(id).orElse(null);

            if (existing == null) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponse("Employee not found"));
            }

            existing.setName(employee.getName());
            existing.setEmail(employee.getEmail());
            existing.setPosition(employee.getPosition());
            existing.setSalary(employee.getSalary());

            Employee updated = repo.save(existing);
            return ResponseEntity.ok(new SuccessResponse<>(updated));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ErrorResponse("Internal Server Error"));
        }
    }

    public ResponseEntity<?> delete(UUID id) {
        try {
            if (!repo.existsById(id)) {
                return ResponseEntity.status(404)
                        .body(new ErrorResponse("Employee not found"));
            }

            repo.deleteById(id);
            return ResponseEntity.ok(new SuccessResponse<>("Employee deleted"));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ErrorResponse("Internal Server Error"));
        }
    }
}
