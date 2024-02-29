import axios from 'axios';

const API_URL = 'http://localhost:8091/api/v1/productos';

const productService = {
    getAllProductos: async () => {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Error buscando productos:', error);
            throw error;
        }
    },

    createProducto: async (formData) => {
        try {
            await axios.post(API_URL, formData);
        } catch (error) {
            console.error('Error creando producto:', error);
            throw error;
        }
    },

    deleteProducto: async (id) => {
        try {
            await axios.delete(`${API_URL}/${id}`);
        } catch (error) {
            console.error('Error eliminando producto:', error);
            throw error;
        }
    },

    updateProducto: async (id, updatedData) => {
        try {
            await axios.put(`${API_URL}/${id}`, updatedData);
        } catch (error) {
            console.error('Error actualizando producto:', error);
            throw error;
        }
    }
};

export default productService;
