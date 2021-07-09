import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';


class UserEdit extends Component {

    emptyItem = {
        username: '',
        email: '',
        education: '',
        workexp: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        
            const user = await (await fetch(`/update/${this.props.match.params.id}`)).json();
            this.setState({item: user});
        
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
        const id = this.props.match.params.id;
        
    
        await fetch(`/update/${id}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/listall');
    }

    render() {
        const {item} = this.state;
        const title = <h2>EDIT USER</h2>;
    
        return <div>
            
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="username">Name</Label>
                        <Input type="text" name="username" id="username" value={item.username || ''}
                               onChange={this.handleChange} autoComplete="username"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" value={item.email || ''}
                               onChange={this.handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="education">Education</Label>
                        <Input type="text" name="education" id="education" value={item.education || ''}
                               onChange={this.handleChange} autoComplete="education"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="workexp">Workexp</Label>
                        <Input type="text" name="workexp" id="workexp" value={item.workexp || ''}
                               onChange={this.handleChange} autoComplete="workexp"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/listall">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(UserEdit);