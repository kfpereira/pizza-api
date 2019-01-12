package com.fantin.pizza.services;

import com.fantin.pizza.config.exceptions.DataRequiredException;
import com.fantin.pizza.config.exceptions.RecordNotFoundException;
import com.fantin.pizza.domain.Sabor;
import com.fantin.pizza.domain.Tamanho;
import com.fantin.pizza.domain.type.TypeTamanho;
import com.fantin.pizza.repositories.SaborRepository;
import com.fantin.pizza.repositories.TamanhoRepository;
import com.fantin.pizza.viewer.FindFirstStepVM;
import com.fantin.pizza.viewer.FirstStepOutVM;
import com.fantin.pizza.viewer.FirstStepVM;
import com.fantin.pizza.viewer.SaborVM;
import com.fantin.pizza.viewer.makers.MakePedido;
import com.fantin.pizza.viewer.makers.MakeSabor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.fantin.pizza.config.errors.ErrorMessages.SABOR_INEXISTENTE;
import static com.fantin.pizza.config.errors.ErrorMessages.SABOR_OBRIGATORIO;

@Service
public class FirstStepService {

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private PedidoService pedidoService;

    public FindFirstStepVM getView() {
        FindFirstStepVM findFirstStepVM = new FindFirstStepVM();
        findFirstStepVM.setTamanhos(Arrays.asList(TypeTamanho.values()));
        findFirstStepVM.setSabores(getSaboresVM());

        return findFirstStepVM;
    }

    private List<SaborVM> getSaboresVM() {
        return saborRepository.findAll()
                .stream()
                .map(MakeSabor::toSaborVM)
                .collect(Collectors.toList());
    }

    public FirstStepOutVM executeOrder(FirstStepVM firstStepVM) throws DataRequiredException, RecordNotFoundException {
        SaborVM saborVM = firstStepVM.getSabor();
        if (saborVM == null || saborVM.getSabor() == null || saborVM.getSabor().isEmpty())
            throw new DataRequiredException(SABOR_OBRIGATORIO.getMessage());

        Sabor sabor = saborRepository.findByDescricao(saborVM.getSabor().toUpperCase());
        if (sabor == null)
            throw new RecordNotFoundException(SABOR_INEXISTENTE.getMessage());

        Tamanho tamanho = tamanhoRepository.findByTypeTamanho(firstStepVM.getTamanho());
        return MakePedido.toFirstStepOut(pedidoService.save(tamanho, sabor));
    }
}
