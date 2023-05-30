package com.cibertec.controller;

import com.cibertec.model.Especialidad;
import com.cibertec.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Especialidad")
public class EspecialidadController {
    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/listarEspecialidades")
    @ResponseBody
    public List<Especialidad> listarEspecialidades(){
        return especialidadService.listarEspecialidades();}
}