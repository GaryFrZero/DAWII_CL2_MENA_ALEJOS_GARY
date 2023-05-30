package com.cibertec.controller;

import com.cibertec.model.Alumno;
import com.cibertec.model.AlumnoRequest;
import com.cibertec.model.Especialidad;
import com.cibertec.model.ResultadoResponse;
import com.cibertec.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/frmAlumno")
    public String frmMantAlumno(Model model){
        model.addAttribute("listaAlumnos",
                alumnoService.listarAlumnos());
        return "Auth/Alumno/frmAlumno";
    }
    @PostMapping("/registrarAlumno")
    @ResponseBody
    public ResultadoResponse registrarAlumno(@RequestBody
                                             AlumnoRequest alumnoRequest){
        String mensaje = "Alumno registrado correctamente";
        Boolean respuesta = true;
        try{
            Alumno objAlumno = new Alumno();
            if(alumnoRequest.getIdalumno() > 0){
                objAlumno.setIdalumno(alumnoRequest.getIdalumno());
            }
            objAlumno.setApealumno(alumnoRequest.getApealumno());
            objAlumno.setNomalumno(alumnoRequest.getNomalumno());
            objAlumno.setLocales(alumnoRequest.getLocales());
            Especialidad objEspecialidad = new Especialidad();
            objEspecialidad.setIdesp(alumnoRequest.getIdesp());
            objAlumno.setEspecialidad(objEspecialidad);
            alumnoService.registrarAlumno(objAlumno);
        }catch (Exception ex){
            mensaje = "Alumno no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder()
                .mensaje(mensaje)
                .respuesta(respuesta).build();

    }

    @DeleteMapping("/eliminarAlumno")
    @ResponseBody
    public ResultadoResponse eliminarAlumno(@RequestBody
                                            AlumnoRequest alumnoRequest){
        String mensaje = "Alumno eliminado correctamente";
        Boolean respuesta = true;
        try{
            alumnoService.eliminarAlumno(alumnoRequest.getIdalumno());
        }catch (Exception ex){
            mensaje = "Alumno no eliminado";
            respuesta = false;
        }
        return ResultadoResponse.builder()
                .mensaje(mensaje).respuesta(respuesta).build();
    }

    @GetMapping("/listarAlumnos")
    @ResponseBody
    public List<Alumno> listarAlumnos(){
        return alumnoService.listarAlumnos();
    }
}