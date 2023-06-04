package com.deyvidsantos.shoppingapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.deyvidsantos.shoppingapi.converter.DTOConverter;
import com.deyvidsantos.shoppingclient.dto.ItemDTO;
import com.deyvidsantos.shoppingclient.dto.ProductDTO;
import com.deyvidsantos.shoppingclient.dto.ShopDTO;
import com.deyvidsantos.shoppingclient.dto.UserDTO;
import org.springframework.stereotype.Service;

import com.deyvidsantos.shoppingapi.models.Shop;
import com.deyvidsantos.shoppingapi.repositories.ShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ProductService productService;
    private final UserService userService;

    /**
     * Obtém todas as lojas.
     *
     * @return Uma lista de objetos ShopDTO representando as lojas.
     */
    public List<ShopDTO> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * Obtém as lojas de um determinado usuário.
     *
     * @param userIdentifier O identificador do usuário.
     * @return Uma lista de objetos ShopDTO representando as lojas do usuário.
     */
    public List<ShopDTO> getShopByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * Obtém as lojas com data maior do que a fornecida.
     *
     * @param shopDTO O objeto ShopDTO contendo a data de referência.
     * @return Uma lista de objetos ShopDTO representando as lojas com data maior
     *         que a fornecida.
     */
    public List<ShopDTO> getShopByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * Encontra uma loja pelo seu ID.
     *
     * @param productId O ID da loja.
     * @return Um objeto ShopDTO representando a loja encontrada ou null se não
     *         encontrada.
     */
    public ShopDTO findShopById(Long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        return shop.map(DTOConverter::convert).orElse(null);
    }

    /**
     * Salva uma nova loja.
     *
     * @param shopDTO O objeto ShopDTO contendo os dados da loja a ser salva.
     * @return Um objeto ShopDTO representando a loja salva.
     */
    public ShopDTO save(ShopDTO shopDTO, String key) {
        UserDTO userDTO = userService
                .getUserByCpfAndKey(shopDTO.getUserIdentifier(), key);
        validateProducts(shopDTO.getItems());
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(ItemDTO::getPrice)
                .reduce((float) 0, Float::sum));
        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    /**
     * Valida os produtos em uma lista de itens.
     *
     * @param items A lista de itens a serem validados.
     * @return true se todos os produtos forem válidos, false caso contrário.
     */
    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService
                    .getProductByIdentifier(
                            item.getProductIdentifier());
            if (productDTO == null) {
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }
}
