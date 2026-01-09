import React, { useState } from 'react';
import { Container, Typography, Button, TextField, Alert, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import api from '../../services/api';

const TradeForm = () => {
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const validationSchema = Yup.object({
    symbol: Yup.string().required('Required'),
    quantity: Yup.number().positive('Must be positive').integer('Must be whole number').required('Required'),
    action: Yup.string().oneOf(['buy', 'sell']).required('Required'),
  });

  const handleSubmit = async (values) => {
    try {
      const endpoint = values.action === 'buy' ? '/trades/buy' : '/trades/sell';
      await api.post(endpoint, { symbol: values.symbol, quantity: values.quantity });
      setSuccess(`${values.action.toUpperCase()} successful`);
      setError('');
    } catch (err) {
      setError('Trade failed');
      setSuccess('');
    }
  };

  return (
    <Container>
      <Typography variant="h4">Trade Stocks</Typography>
      {error && <Alert severity="error">{error}</Alert>}
      {success && <Alert severity="success">{success}</Alert>}
      <Formik initialValues={{ symbol: '', quantity: '', action: 'buy' }} validationSchema={validationSchema} onSubmit={handleSubmit}>
        {({ errors, touched }) => (
          <Form>
            <Field as={TextField} name="symbol" label="Stock Symbol (e.g., AAPL)" fullWidth margin="normal" error={touched.symbol && errors.symbol} helperText={touched.symbol && errors.symbol} />
            <Field as={TextField} name="quantity" label="Quantity" type="number" fullWidth margin="normal" error={touched.quantity && errors.quantity} helperText={touched.quantity && errors.quantity} />
            <FormControl fullWidth margin="normal">
              <InputLabel>Action</InputLabel>
              <Field as={Select} name="action">
                <MenuItem value="buy">Buy</MenuItem>
                <MenuItem value="sell">Sell</MenuItem>
              </Field>
            </FormControl>
            <Button type="submit" variant="contained" fullWidth>Execute Trade</Button>
          </Form>
        )}
      </Formik>
    </Container>
  );
};

export default TradeForm;