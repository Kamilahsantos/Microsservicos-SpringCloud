package com.codaretalks.api.controller;


import com.codaretalks.api.exception.ResourceNotFoundException;
import com.codaretalks.api.model.Student;
import com.codaretalks.api.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StudentsController {

    private Logger log = LoggerFactory.getLogger((this.getClass()));


    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        log.info("Listando todos os estudantes cadastrados");
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        log.error("Estudante com id {}  nao encontrado", studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found :: " + studentId));
        log.info("Estudante com id {}  encontrado com sucesso",studentId);
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        log.info("Estudante com nome {}  e level {} cadastrado com sucesso", student.getName(), student.getLevel());
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity <Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                   @Valid @RequestBody Student studentDetails)
            throws ResourceNotFoundException {
        log.error("Estudante com id {}  nao encontrado", studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        student.setName(studentDetails.getName());
        student.setLevel(studentDetails.getLevel());
        final Student updateStudent = studentRepository.save(student);
        log.info("Estudante com id {} nome {}  e level {} atualizado com sucesso", student.getId(), student.getName(), student.getLevel());
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/students/{id}")
    public Map< String, Boolean > deleteStudent(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        log.error("Estudante com id {}  nao encontrado", studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        studentRepository.delete(student);
        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        log.info("Estudante com id {} excluido com sucesso", student.getId());
        return response;
    }



}
