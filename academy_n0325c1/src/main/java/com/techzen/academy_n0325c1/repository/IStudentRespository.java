package com.techzen.academy_n0325c1.repository;

import com.techzen.academy_n0325c1.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStudentRespository extends JpaRepository<Student, Integer> {
//    List<Student> findByNameContainingAndScoreBetween(String name,double fromScore,double toScore);
//@Query(value = """
//             FROM Student WHERE name LIKE concat('%', :name, '%')
//             AND (:fromScore IS NULL OR score >= :fromScore )
//             AND (:toScore IS NULL OR score <= :toScore)
//            """) // HQL
//List<Student> findByAttr(String name , Double fromScore , Double toScore);
//}

    @Query(value = """
            SELECT * FROM Student WHERE name LIKE concat('%', :name, '%')
             AND (:fromScore IS NULL OR score >= :fromScore )
             AND (:toScore IS NULL OR score <= :toScore)
            """, nativeQuery = true) // MySQL -> Khong phan biet hoa thuong
    Page<Student> findByAttr(String name , Double fromScore , Double toScore, Pageable pageable);
}
