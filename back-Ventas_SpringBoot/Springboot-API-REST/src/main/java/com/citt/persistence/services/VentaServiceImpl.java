package com.citt.persistence.services;

import com.citt.exceptions.VentaNotFoundException;
import com.citt.persistence.entity.Venta;
import com.citt.persistence.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> findAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta saveVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Long idVenta, Venta venta) throws VentaNotFoundException {
        Optional<Venta> optionalVenta = ventaRepository.findById(idVenta);

        if (optionalVenta.isEmpty()) {
            throw new VentaNotFoundException("No es posible actualizar. No existe venta con ID: " + idVenta);
        }

        Venta ventaDB = optionalVenta.get();

        if (Objects.nonNull(venta.getDireccionCompra()) && !venta.getDireccionCompra().trim().isEmpty()) {
            ventaDB.setDireccionCompra(venta.getDireccionCompra());
        }

        if (Objects.nonNull(venta.getValorCompra())) {
            ventaDB.setValorCompra(venta.getValorCompra());
        }

        if (Objects.nonNull(venta.getFechaCompra())) {
            ventaDB.setFechaCompra(venta.getFechaCompra());
        }

        if (Objects.nonNull(venta.getDespachoGenerado())) {
            ventaDB.setDespachoGenerado(venta.getDespachoGenerado());
        }

        return ventaRepository.save(ventaDB);
    }

    @Override
    public void deleteVenta(Long idVenta) throws VentaNotFoundException {
        Optional<Venta> venta = ventaRepository.findById(idVenta);

        if (venta.isEmpty()) {
            throw new VentaNotFoundException("No es posible eliminar. No existe venta con ID: " + idVenta);
        }

        ventaRepository.deleteById(idVenta);
    }

    @Override
    public Venta findById(Long idVenta) throws VentaNotFoundException {
        Optional<Venta> venta = ventaRepository.findById(idVenta);

        if (venta.isEmpty()) {
            throw new VentaNotFoundException("Venta no encontrada con ID: " + idVenta);
        }

        return venta.get();
    }
}
