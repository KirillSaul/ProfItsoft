import Button from "@material-ui/core/Button";
import React from 'react';

class ButtonOperation extends React.Component {
    render() {
        return <Button variant="contained" color="primary"
                       onClick={() => this.props.onClick(this.props.buttonName)}>{this.props.buttonName}</Button>;
    }
}

export default ButtonOperation;