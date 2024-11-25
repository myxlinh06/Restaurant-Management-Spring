import axios from 'axios';

const API_URL = 'http://localhost:8080/api'; // Thay đổi URL này theo địa chỉ backend của bạn

export interface OrderItem {
  menuItemId: number;
  quantity: number;
}

export interface OrderRequest {
  tableNumber: string;
  items: OrderItem[];
  specialInstructions?: string;
}

export interface Order {
  id: number;
  tableNumber: string;
  items: OrderItem[];
  specialInstructions?: string;
  status: string;
  createdAt: string;
}

export const createOrder = async (orderRequest: OrderRequest): Promise<Order> => {
  try {
    const response = await axios.post<Order>(`${API_URL}/orders`, orderRequest);
    return response.data;
  } catch (error) {
    console.error('Error creating order:', error);
    throw error;
  }
};

export const getOrder = async (orderId: number): Promise<Order> => {
  try {
    const response = await axios.get<Order>(`${API_URL}/orders/${orderId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching order:', error);
    throw error;
  }
};

export const updateOrder = async (orderId: number, orderRequest: Partial<OrderRequest>): Promise<Order> => {
  try {
    const response = await axios.put<Order>(`${API_URL}/orders/${orderId}`, orderRequest);
    return response.data;
  } catch (error) {
    console.error('Error updating order:', error);
    throw error;
  }
};

export const deleteOrder = async (orderId: number): Promise<void> => {
  try {
    await axios.delete(`${API_URL}/orders/${orderId}`);
  } catch (error) {
    console.error('Error deleting order:', error);
    throw error;
  }
};