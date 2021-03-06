package com.ly.service;

import com.ly.entities.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    public boolean add(Dept dept);
    public Dept    get(Long id);
    public List<Dept> list();

}
