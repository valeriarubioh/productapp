import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';

const ProductArchive = () => {
    const [productos, setProductos] = useState([]);
    const [formData, setFormData] = useState({
        nombre: '',
        descripcion: '',
        precio: 0,
        stock: 0
    });
    const [updateProducto, setUpdateProducto] = useState(null);
    const [combinaciones, setCombinaciones] = useState([]);
    const [maxSumValue, setMaxSumValue] = useState(10);

    const [fetchingCombinaciones, setFetchingCombinaciones] = useState(false);
    const [statistics, setStatistics] = useState({});

    const fetchProductos = async () => {
        try {
            const response = await axios.get('http://localhost:8091/api/v1/productos');
            setProductos(response.data);
        } catch (error) {
            console.error('Error buscando productos:', error);
        }
    };

    const fetchCombinaciones = useCallback(async () => {
        try {
            const response = await axios.get(`http://localhost:8091/api/v1/productos/productosCombinaciones?precioMaximo=${maxSumValue}`);
            setCombinaciones(response.data.maxCombinacionesProductos);
        } catch (error) {
            console.error('Error buscando combinaciones:', error);
        } finally {
            setFetchingCombinaciones(false);
        }
    }, [maxSumValue]);

    const fetchStatistics = async () => {
        try {
            const response = await axios.get('http://localhost:8091/api/v1/productos/estadisticas');
            setStatistics(response.data);
        } catch (error) {
            console.error('Error buscando estadisticas:', error);
        }
    };

    useEffect(() => {
        fetchProductos();
    }, []);

    useEffect(() => {
        if (fetchingCombinaciones) {
            fetchCombinaciones();
        }
    }, [fetchingCombinaciones, fetchCombinaciones]);

    useEffect(() => {
        fetchStatistics();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8091/api/v1/productos', formData);
            fetchProductos();
            setFormData({ nombre: '', descripcion: '', precio: 0, stock: 0 });
            setFetchingCombinaciones(true);
        } catch (error) {
            console.error('Error creando el producto:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8091/api/v1/productos/${id}`);
            fetchProductos();
            setFetchingCombinaciones(true);
        } catch (error) {
            console.error('Error eliminando producto:', error);
        }
    };

    const handleUpdate = async (id) => {
        try {
            await axios.put(`http://localhost:8091/api/v1/productos/${id}`, updateProducto);
            fetchProductos();
            setUpdateProducto(null);
            setFetchingCombinaciones(true);
        } catch (error) {
            console.error('Error actualizando producto:', error);
        }
    };

    const handleFetchCombinaciones = () => {
        setFetchingCombinaciones(true);
    };

    return (
        <div>
            <h2>Productos</h2>
            <h3>Estadisticas de productos</h3>
            <ul>
                <li>Producto con más inventario:</li>
                    <ul>
                        <li>Nombre: {statistics.maximoProducto?.nombre}</li>
                        <li>Precio: {statistics.maximoProducto?.precio}</li>
                        <li>Descripción: {statistics.maximoProducto?.stock}</li>
                    </ul>
                <li>Total valor de inventario: {statistics.valorTotalInventario}</li>
            </ul>
            <h3>Combinaciones de Productos</h3>
            <p>Valor Máximo actual: {maxSumValue}</p>
            <input
                type="number"
                value={maxSumValue}
                onChange={(e) => setMaxSumValue(parseInt(e.target.value))}
                placeholder="Coloca un valor máximo"
            />
            <button onClick={handleFetchCombinaciones}>Buscar Combinaciones</button>
            <ul>
                {combinaciones.map((combinacion, index) => (
                    <li key={index}>{combinacion.join(', ')}</li>
                ))}
            </ul>
            <ul>
                {productos.map((producto) => (
                    <li key={producto.id}>
                        {updateProducto && updateProducto.id === producto.id ? (
                            <form onSubmit={() => handleUpdate(producto.id)}>
                                <input type="text" name="nombre" value={updateProducto.nombre} onChange={(e) => setUpdateProducto({ ...updateProducto, nombre: e.target.value })} />
                                <input type="text" name="descripcion" value={updateProducto.descripcion} onChange={(e) => setUpdateProducto({ ...updateProducto, descripcion: e.target.value })} />
                                <input type="number" name="precio" value={updateProducto.precio} onChange={(e) => setUpdateProducto({ ...updateProducto, precio: e.target.value })} />
                                <input type="number" name="stock" value={updateProducto.stock} onChange={(e) => setUpdateProducto({ ...updateProducto, stock: e.target.value })} />
                                <button type="submit">Guardar</button>
                            </form>
                        ) : (
                            <>
                                {producto.nombre} - {producto.descripcion} - ${producto.precio} - Stock: {producto.stock}
                                <button onClick={() => handleDelete(producto.id)}>Eliminar</button>
                                <button onClick={() => setUpdateProducto(producto)}>Editar</button>
                            </>
                        )}
                    </li>
                ))}
            </ul>
            <form onSubmit={handleFormSubmit}>
                <input type="text" name="nombre" placeholder="Nombre" value={formData.nombre} onChange={handleInputChange} />
                <input type="text" name="descripcion" placeholder="Descripción" value={formData.descripcion} onChange={handleInputChange} />
                <input type="number" name="precio" placeholder="Precio" value={formData.precio} onChange={handleInputChange} />
                <input type="number" name="stock" placeholder="Stock" value={formData.stock} onChange={handleInputChange} />
                <button type="submit">Agregar Producto</button>
            </form>
        </div>
    );
};

export default ProductArchive;



