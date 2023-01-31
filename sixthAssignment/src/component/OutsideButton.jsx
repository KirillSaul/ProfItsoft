import React from 'react';
import Button from "@material-ui/core/Button";

class OutsideButton extends React.Component {
    render() {
        return <Button variant="contained" color="secondary"
                       onClick={() => this.props.onClick()}>{this.props.buttonName}</Button>;
    }
}

export default OutsideButton;