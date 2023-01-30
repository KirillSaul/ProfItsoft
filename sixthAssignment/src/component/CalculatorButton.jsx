import Button from "@material-ui/core/Button";
import React from 'react';

class CalculatorButton extends React.Component {
    render() {
        return <Button variant="contained" color="primary"
                       onClick={() => this.props.changeOutput(this.props.buttonName)}>{this.props.buttonName}</Button>;
    }
}

export default CalculatorButton;