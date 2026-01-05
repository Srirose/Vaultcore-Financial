import React, { useState } from 'react';
import { Container, Typography, Button, TextField, Alert } from '@mui/material';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import api from '../../services/api';

const SendMoneyWizard = () => {
  const [step, setStep] = useState(1);
  const [error, setError] = useState('');
  const [twoFA, setTwoFA] = useState(false);

  const validationSchema = Yup.object({
    recipient: Yup.string().required('Required'),
    amount: Yup.number().positive('Must be positive').required('Required'),
  });

  const handleSubmit = async (values) => {
    try {
      if (values.amount > 1000) setTwoFA(true); // Trigger 2FA for large amounts
      await api.post('/transactions/transfer', values); // Backend handles ledger
      alert('Transfer successful');
    } catch (err) {
      setError('Transfer failed');
    }
  };

  return (
    <Container>
      <Typography variant="h4">Send Money</Typography>
      {error && <Alert severity="error">{error}</Alert>}
      {twoFA && <Alert severity="info">2FA required: Check SMS/Email</Alert>}
      <Formik initialValues={{ recipient: '', amount: '' }} validationSchema={validationSchema} onSubmit={handleSubmit}>
        {({ errors, touched }) => (
          <Form>
            <Field as={TextField} name="recipient" label="Recipient Account" fullWidth margin="normal" error={touched.recipient && errors.recipient} helperText={touched.recipient && errors.recipient} />
            <Field as={TextField} name="amount" label="Amount" type="number" fullWidth margin="normal" error={touched.amount && errors.amount} helperText={touched.amount && errors.amount} />
            <Button type="submit" variant="contained">Send</Button>
          </Form>
        )}
      </Formik>
    </Container>
  );
};

export default SendMoneyWizard;