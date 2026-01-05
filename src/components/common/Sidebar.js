import React from 'react';
import { List, ListItem, ListItemText } from '@mui/material';
import { Link } from 'react-router-dom';

const Sidebar = () => (
  <div style={{ width: 200, backgroundColor: '#f4f4f4', height: '100vh' }}>
    <List>
      <ListItem button component={Link} to="/dashboard">
        <ListItemText primary="Dashboard" />
      </ListItem>
      <ListItem button component={Link} to="/transactions">
        <ListItemText primary="Transactions" />
      </ListItem>
      <ListItem button component={Link} to="/send-money">
        <ListItemText primary="Send Money" />
      </ListItem>
      <ListItem button component={Link} to="/portfolio">
        <ListItemText primary="Portfolio" />
      </ListItem>
      <ListItem button component={Link} to="/trade">
        <ListItemText primary="Trade Stocks" />
      </ListItem>
    </List>
  </div>
);

export default Sidebar;