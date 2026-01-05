import React, { useEffect, useState } from 'react';
import { Container, Typography } from '@mui/material';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';
import api from '../../services/api';

const Portfolio = () => {
  const [portfolio, setPortfolio] = useState([]);

  useEffect(() => {
    const fetchPortfolio = async () => {
      const response = await api.get('/portfolio'); // Backend endpoint
      setPortfolio(response.data);
    };
    fetchPortfolio();
  }, []);

  return (
    <Container>
      <Typography variant="h4">Portfolio</Typography>
      <LineChart width={600} height={300} data={portfolio}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="value" stroke="#8884d8" />
      </LineChart>
    </Container>
  );
};

export default Portfolio;