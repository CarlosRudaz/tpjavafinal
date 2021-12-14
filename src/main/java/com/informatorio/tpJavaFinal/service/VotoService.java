package com.informatorio.tpJavaFinal.service;

import java.util.List;

import com.informatorio.tpJavaFinal.entity.Voto;
import com.informatorio.tpJavaFinal.dto.VotoDto;
import com.informatorio.tpJavaFinal.entity.Emprendimiento;
import com.informatorio.tpJavaFinal.repository.EmprendimientoRepository;
import com.informatorio.tpJavaFinal.repository.VotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;



@Service

public class VotoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final Converter<VotoDto, Voto> votoDTOVotoConverter;
    private final VotoRepository votoRepository;
    @Autowired
    public VotoService(EmprendimientoRepository emprendimientoRepository, 
                       Converter<VotoDto, Voto> votoDTOVotoConverter, 
                       VotoRepository votoRepository) {
        this.emprendimientoRepository = emprendimientoRepository; 
        this.votoDTOVotoConverter = votoDTOVotoConverter;
        this.votoRepository = votoRepository;
    }
    public boolean chequearVoto(VotoDto votoDTO){
        Voto voto = votoDTOVotoConverter.convert(votoDTO);
        return votoRepository.findAll().stream().anyMatch(v -> {
            assert voto != null;
            return v.getUsuarioId().equals(voto.getUsuarioId()) && v.getEmprendimientoId().equals(voto.getEmprendimientoId());
        });
    }
    public Boolean crearVoto(VotoDto votoDTO) {
        Voto voto = votoDTOVotoConverter.convert(votoDTO);
        if(!chequearVoto(votoDTO)){
            assert voto != null;
            Emprendimiento emprendimiento = emprendimientoRepository.getById(voto.getEmprendimientoId());
            emprendimiento.setContadorDeVotos(emprendimiento.getContadorDeVotos()+1);
            emprendimientoRepository.save(emprendimiento);
            votoRepository.save(voto);
            return true;
        }
        return false;
    }
    public List<Voto> obtenerVotos(Long usuarioId) {
        return votoRepository.findByUsuarioId(usuarioId);
    }   
}