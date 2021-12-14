package com.informatorio.tpJavaFinal.convert;

import java.time.LocalDateTime;

import com.informatorio.tpJavaFinal.dto.VotoDto;
import com.informatorio.tpJavaFinal.entity.Voto;
import com.informatorio.tpJavaFinal.repository.EmprendimientoRepository;
import com.informatorio.tpJavaFinal.repository.UsuarioRepository;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class VotoConverter implements Converter<VotoDto, Voto> {
    public VotoConverter(EmprendimientoRepository emprendimientoRepository, UsuarioRepository usuarioRepository) {
    }
    @Override
    public Voto convert(VotoDto votoDto) {
        Voto voto = new Voto();
        voto.setGenerado(votoDto.getGenerado());
        voto.setUsuarioId(votoDto.getUsuarioId());
        voto.setEmprendimientoId(votoDto.getEmprendimientoId());
        voto.setFechaDeCreacion(LocalDateTime.now());
        return voto;
    } 
}