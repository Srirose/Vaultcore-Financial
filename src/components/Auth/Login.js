import React, { useState } from 'react';
import { TextField, Button, Container, Typography, Alert } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { login } from '../../services/auth';

const Login = () => {
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const validationSchema = Yup.object({
    email: Yup.string().email('Invalid email').required('Required'),
    password: Yup.string().required('Required'),
  });

  const handleSubmit = async (values) => {
    try {
      await login(values);
      navigate('/dashboard');
    } catch (err) {
      setError('Invalid credentials');
    }
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4">Login</Typography>
      {error && <Alert severity="error">{error}</Alert>}
      <Formik initialValues={{ email: '', password: '' }} validationSchema={validationSchema} onSubmit={handleSubmit}>
        {({ errors, touched }) => (
          <Form>
            <Field as={TextField} name="email" label="Email" fullWidth margin="normal" error={touched.email && errors.email} helperText={touched.email && errors.email} />
            <Field as={TextField} name="password" label="Password" type="password" fullWidth margin="normal" error={touched.password && errors.password} helperText={touched.password && errors.password} />
            <Button type="submit" variant="contained" fullWidth>Login</Button>
          </Form>
        )}
      </Formik>
    </Container>
  );
};

export default Login;