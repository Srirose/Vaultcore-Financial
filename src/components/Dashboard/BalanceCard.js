import React from 'react';
import { Card, CardContent, Typography } from '@mui/material';

const BalanceCard = ({ balance }) => (
  <Card>
    <CardContent>
      <Typography variant="h5">Account Balance</Typography>
      <Typography variant="h4">${balance}</Typography>
    </CardContent>
  </Card>
);

export default BalanceCard;