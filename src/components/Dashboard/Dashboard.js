import React, { useEffect, useState } from 'react';
import { Container, Typography } from '@mui/material';
import api from '../../services/api';
import BalanceCard from './BalanceCard';

const Dashboard = () => {
  const [balance, setBalance] = useState(0);

  useEffect(() => {
    const fetchBalance = async () => {
      const response = await api.get('/accounts/balance'); // Backend endpoint
      setBalance(response.data.balance);
    };
    fetchBalance();
  }, []);

  return (
    <Container>
      <Typography variant="h4">Dashboard</Typography>
      <BalanceCard balance={balance} />
      {/* Add recent transactions summary here */}
    </Container>
  );
};

export default Dashboard;