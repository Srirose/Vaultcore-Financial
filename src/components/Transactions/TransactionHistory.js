import React, { useEffect, useState } from 'react';
import { Container, Typography, List, ListItem, ListItemText } from '@mui/material';
import api from '../../services/api';

const TransactionHistory = () => {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    const fetchTransactions = async () => {
      const response = await api.get('/transactions'); // Backend endpoint
      setTransactions(response.data);
    };
    fetchTransactions();
  }, []);

  return (
    <Container>
      <Typography variant="h4">Transaction History</Typography>
      <List>
        {transactions.map((tx) => (
          <ListItem key={tx.id}>
            <ListItemText primary={`${tx.type}: $${tx.amount}`} secondary={tx.date} />
          </ListItem>
        ))}
      </List>
    </Container>
  );
};

export default TransactionHistory;