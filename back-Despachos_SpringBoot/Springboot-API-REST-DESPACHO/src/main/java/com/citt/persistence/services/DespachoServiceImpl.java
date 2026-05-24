package com.citt.persistence.services;

import com.citt.exceptions.DespachoNotFoundException;
import com.citt.persistence.entity.Despacho;
import com.citt.persistence.repository.DespachoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DespachoServiceImpl implements DespachoService {

    @Autowired
    private DespachoRepository despachoRepository;

    @Override
    public List<Despacho> findAllDespachos() {
        return despachoRepository.findAll();
    }

    @Override
    public Despacho saveDespacho(Despacho despacho) {
        if (despacho.getIntento() == null) {
            despacho.setIntento(0);
        }

        if (despacho.getDespachado() == null) {
            despacho.setDespachado(false);
        }

        return despachoRepository.save(despacho);
    }

    @Override
    public Despacho updateDespacho(Long idDespacho, Despacho despacho) throws DespachoNotFoundException {
        Optional<Despacho> optionalDespacho = despachoRepository.findById(idDespacho);

        if (optionalDespacho.isEmpty()) {
            throw new DespachoNotFoundException("Despacho no encontrado con ID: " + idDespacho);
        }

        Despacho despachoDB = optionalDespacho.get();

        if (Objects.nonNull(despacho.getFechaDespacho())) {
            despachoDB.setFechaDespacho(despacho.getFechaDespacho());
        }

        if (Objects.nonNull(despacho.getPatenteCamion()) && !despacho.getPatenteCamion().trim().isEmpty()) {
            despachoDB.setPatenteCamion(despacho.getPatenteCamion());
        }

        if (Objects.nonNull(despacho.getIntento())) {
            despachoDB.setIntento(despacho.getIntento());
        }

        if (Objects.nonNull(despacho.getIdCompra())) {
            despachoDB.setIdCompra(despacho.getIdCompra());
        }

        if (Objects.nonNull(despacho.getDireccionCompra()) && !despacho.getDireccionCompra().trim().isEmpty()) {
            despachoDB.setDireccionCompra(despacho.getDireccionCompra());
        }

        if (Objects.nonNull(despacho.getValorCompra())) {
            despachoDB.setValorCompra(despacho.getValorCompra());
        }

        if (Objects.nonNull(despacho.getDespachado())) {
            despachoDB.setDespachado(despacho.getDespachado());
        }

        return despachoRepository.save(despachoDB);
    }

    @Override
    public void deleteDespacho(Long idDespacho) throws DespachoNotFoundException {
        Optional<Despacho> despacho = despachoRepository.findById(idDespacho);

        if (despacho.isEmpty()) {
            throw new DespachoNotFoundException("No es posible eliminar. No existe despacho con ID: " + idDespacho);
        }

        despachoRepository.deleteById(idDespacho);
    }

    @Override
    public Despacho findById(Long idDespacho) throws DespachoNotFoundException {
        Optional<Despacho> despacho = despachoRepository.findById(idDespacho);

        if (despacho.isEmpty()) {
            throw new DespachoNotFoundException("No existe despacho con ID: " + idDespacho);
        }

        return despacho.get();
    }
}
