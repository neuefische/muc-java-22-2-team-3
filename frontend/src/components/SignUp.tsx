import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import { LoginData } from '../model/LoginData';


const theme = createTheme();
type SignUpProps = {
    adduser(newUser: LoginData): void;
}

export default function SignUp(props: SignUpProps) {

    const emptyInput: LoginData = {
        "username": "",
        "password": "",
        "firstname": "",
        "lastname": ""
    }


    const[submitted, setSubmitted] = useState<boolean>(false);
    const[error, setError] = useState<boolean>(false);

    const [inputValue, setInputValue] = useState(emptyInput)

    useEffect(()=>{

    }, [inputValue])

    const handleSubmit = (event: FormEvent) => {
        event.preventDefault()
        if(inputValue.username === "" || inputValue.password === ""){
            setError(true);
        }else
        {
            setSubmitted(true);
            setError(false);
            props.adduser(inputValue)
            setInputValue(emptyInput)
        }

    }

    function handleOnChange(event: ChangeEvent<HTMLInputElement>){
        const fieldName = event.target.name
        const fieldValue = event.target.value

        setInputValue(prevState => ({
            ...prevState, [fieldName]: fieldValue
        }))
    }




    const successMessage = () => {
        return (
            <div
                className="success"
                style={{
                    display: submitted ? '' : 'none',
                }}>
                <h1>User {inputValue.username} successfully registered!!</h1>
            </div>
        );
    };
    const errorMessage = () => {
        return (
            <div
                className="error"
                style={{
                    display: error ? '' : 'none',
                }}>
                <h1>Please enter all the fields</h1>
            </div>
        );
    };

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>

                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign up
                    </Typography>
                    <div className="messages">
                        {errorMessage()}
                        {successMessage()}
                    </div>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="firstname"
                                    required
                                    fullWidth
                                    id="firstName"
                                    label="First Name"
                                    autoFocus
                                    value={inputValue.firstname}
                                    onChange ={handleOnChange}
                                />
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="lastName"
                                    label="Last Name"
                                    name="lastname"
                                    autoComplete="family-name"
                                    value={inputValue.lastname}
                                    onChange ={handleOnChange}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="username"
                                    label="Username"
                                    name="username"
                                    autoComplete="username"
                                    value={inputValue.username}
                                    onChange ={handleOnChange}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="password"
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="new-password"
                                    value={inputValue.password}
                                    onChange ={handleOnChange}
                                />
                            </Grid>

                        </Grid>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}

                        >
                            Sign Up
                        </Button>

                    </Box>
                </Box>
                <Typography variant="body2" color="text.secondary" align="center" {...props}>
                    {'Copyright © '}
                    <Typography component="h1" variant="h5">
                        Team 3
                    </Typography>{' '}
                    {new Date().getFullYear()}
                    {'.'}
                </Typography>
            </Container>
        </ThemeProvider>
    );
}

